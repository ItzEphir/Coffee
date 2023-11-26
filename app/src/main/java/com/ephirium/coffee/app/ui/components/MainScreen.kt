package com.ephirium.coffee.app.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.coffee.app.R
import com.ephirium.coffee.app.presentation.ComplimentViewModel
import com.ephirium.coffee.app.ui.activity.MainActivity
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun MainScreen() {
    val viewModel: ComplimentViewModel = koinViewModel()
    val compliment by viewModel.compliment.collectAsStateWithLifecycle()

    val activity = LocalContext.current as MainActivity
    activity.setAlarm()

    MainScreenLayout(compliment = compliment, animationDuration = 500.milliseconds, block = {
        viewModel.changeCompliment()
    })
}

@Composable
private fun MainScreenLayout(
    compliment: String = String(),
    animationDuration: Duration = 0.milliseconds,
    block: () -> Unit,
) {
    ConstraintLayout {
        val (card, button) = createRefs()

        var visible by remember { mutableStateOf(false) }
        var changeCompliment by remember { mutableStateOf(false) }

        LaunchedEffect(key1 = changeCompliment) {
            visible = false
            delay(animationDuration)
            block()
            visible = true
        }

        Box(modifier = Modifier.constrainAs(card) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(button.top)
        }) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + slideIn(initialOffset = { size ->
                    IntOffset(size.width / 4, size.height / 4)
                }),
                exit = fadeOut() + slideOut(targetOffset = { size ->
                    IntOffset(-size.width / 4, -size.height / 4)
                }),
            ) {
                ComplimentCard(compliment)
            }
        }
        Button(modifier = Modifier
            .constrainAs(button) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(all = 4.dp)
            .padding(bottom = 8.dp)
            .wrapContentSize(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            contentPadding = PaddingValues(8.dp),
            enabled = visible,
            onClick = {
                changeCompliment = !changeCompliment
            }) {
            Text(
                text = stringResource(R.string.pour_coffee),
                modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreenLayout(compliment = "You are ok", animationDuration = 0.milliseconds, block = {})
}