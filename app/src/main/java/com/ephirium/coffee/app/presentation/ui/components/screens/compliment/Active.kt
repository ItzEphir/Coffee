package com.ephirium.coffee.app.presentation.ui.components.screens.compliment

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ephirium.coffee.app.R
import com.ephirium.coffee.app.presentation.model.UiCompliment
import com.ephirium.coffee.app.presentation.state.ComplimentScreenState.Active
import com.ephirium.coffee.app.presentation.ui.theme.CoffeeTheme

@Composable
fun Active(state: Active, onSwapClicked: () -> Unit) {
    
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (compliment, swapButton) = createRefs()
        
        AnimatedVisibility(
            modifier = Modifier
                .constrainAs(compliment) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth(),
            visible = state.isVisible,
            enter = fadeIn() + slideIn {
                IntOffset(it.width / 4, it.height / 4)
            },
            exit = fadeOut() + slideOut {
                IntOffset(-it.width / 4, -it.height / 4)
            },
        ) {
            ComplimentCard(
                text = state.compliment.text[Locale.current.language] ?: "",
                modifier = Modifier.wrapContentSize()
            )
        }
        
        Button(
            modifier = Modifier
                .constrainAs(swapButton) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(all = 8.dp)
                .padding(bottom = 12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            onClick = onSwapClicked, enabled = state.isVisible,
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = stringResource(id = R.string.pour_coffee),
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@PreviewScreenSizes
@Preview(showBackground = true)
@Composable
internal fun ActivePreview() {
    CoffeeTheme {
        Active(
            Active(
                isVisible = true, compliment = UiCompliment(
                    id = "",
                    text = mapOf("ru" to "Ты такая красивая", "en" to "You are so beautiful")
                )
            )
        ) {}
    }
}