package com.ephirium.coffee.feature.compliment.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.IntrinsicSize.Max
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ephirium.coffee.feature.compliment.presentation.model.ComplimentUiModel
import com.ephirium.coffee.preview.ThemePreview
import com.ephirium.coffee.theme.CoffeeTheme

@Composable
internal fun ComplimentCard(complimentUiModel: ComplimentUiModel, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.height(Max),
        colors = cardColors(
            containerColor = colorScheme.primaryContainer,
            contentColor = colorScheme.primary,
        ),
    ) {
        Column(
            modifier = modifier.padding(horizontal = 32.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = complimentUiModel.text,
                style = typography.titleMedium,
                fontWeight = ExtraBold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "<3",
                style = typography.titleLarge,
                fontWeight = ExtraBold,
            )
        }
    }
}

private class ComplimentProvider : PreviewParameterProvider<ComplimentUiModel> {
    override val values: Sequence<ComplimentUiModel> = sequenceOf(
        ComplimentUiModel(text = "I love you")
    )
}

@ThemePreview
@Composable
private fun ComplimentPreview(
    @PreviewParameter(ComplimentProvider::class) complimentUiModel: ComplimentUiModel,
) {
    CoffeeTheme {
        Surface(color = colorScheme.background) {
            ComplimentCard(complimentUiModel)
        }
    }
}