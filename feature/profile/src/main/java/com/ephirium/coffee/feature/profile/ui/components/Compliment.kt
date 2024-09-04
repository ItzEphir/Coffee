package com.ephirium.coffee.feature.profile.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ephirium.coffee.feature.profile.presentation.model.ComplimentUiModel
import com.ephirium.coffee.preview.ThemePreview
import com.ephirium.coffee.theme.CoffeeTheme

@Composable
internal fun Compliment(complimentUiModel: ComplimentUiModel, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(modifier = modifier.padding(8.dp)) {
            OutlinedCard(modifier = Modifier.align(Alignment.End)) {
                Box(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
                    Text(
                        text = complimentUiModel.languageCode,
                        style = typography.bodyMedium,
                        fontWeight = Bold,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = complimentUiModel.text,
                modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

private class ComplimentUiModelProvider : PreviewParameterProvider<ComplimentUiModel> {
    @Suppress("SpellCheckingInspection")
    override val values: Sequence<ComplimentUiModel> = sequenceOf(
        ComplimentUiModel(text = "You are so beautiful!", languageCode = "en"),
        ComplimentUiModel(text = "Ты такая красивая!", languageCode = "ru")
    )
}

@ThemePreview
@Composable
private fun ComplimentPreview(@PreviewParameter(ComplimentUiModelProvider::class) complimentUiModel: ComplimentUiModel) {
    CoffeeTheme {
        Surface(color = colorScheme.background) {
            Compliment(complimentUiModel = complimentUiModel, modifier = Modifier.fillMaxWidth())
        }
    }
}
