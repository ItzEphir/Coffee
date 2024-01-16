package com.ephirium.coffee.app.ui.components

import android.icu.util.Calendar
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.coffee.app.R
import com.ephirium.coffee.app.notification.DailyCoffeeHelper
import com.ephirium.coffee.app.presentation.viewmodel.ComplimentViewModel
import com.ephirium.coffee.app.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun MainScreen() {
    val viewModel: ComplimentViewModel = koinViewModel()
    val compliment by viewModel.compliment.collectAsStateWithLifecycle()
    val viewModel2: MainViewModel = koinViewModel()

    LaunchedEffect(key1 = Unit, block = {
        viewModel2.mainScreenState.collect {
            if (it.compliment == null) {

            }
        }
    })

    MainScreenLayout(compliment = compliment, animationDuration = 300.milliseconds, block = {
        viewModel.changeCompliment()
    })
}

@Composable
private fun MainScreenLayout(
    compliment: String = String(),
    animationDuration: Duration = 0.milliseconds,
    block: () -> Unit = {},
    dailyCoffeeHelper: DailyCoffeeHelper? = koinInject(),
) {
    var isVisible by rememberSaveable { mutableStateOf(true) }

    val scope = rememberCoroutineScope()

    ConstraintLayout {
        val (card, button, notificationButton) = createRefs()

        Box(modifier = Modifier.constrainAs(card) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }) {
            AnimatedVisibility(
                visible = isVisible,
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
                bottom.linkTo(notificationButton.top)
            }
            .padding(all = 4.dp)
            .wrapContentSize(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            contentPadding = PaddingValues(8.dp),
            enabled = isVisible,
            onClick = {
                scope.launch {
                    isVisible = false
                    delay(animationDuration)
                    block()
                    isVisible = true
                }
            }) {
            Text(
                text = stringResource(R.string.pour_coffee),
                modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Button(modifier = Modifier
            .constrainAs(notificationButton) {
                start.linkTo(button.start)
                end.linkTo(button.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(all = 4.dp)
            .padding(bottom = 8.dp)
            .wrapContentSize(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            contentPadding = PaddingValues(8.dp),
            onClick = {
                dailyCoffeeHelper?.setAlarm(
                    Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, 10)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                    },
                )
            }) {
            Text(
                text = "Set Alarm",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(
    showBackground = true, device = "spec:width=1920dp,height=1080dp,dpi=160"
)
@Composable
private fun MainScreenPreview() {
    MainScreenLayout(
        compliment = "You are ok", dailyCoffeeHelper = null
    )
}