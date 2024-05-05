package com.ephirium.coffee.feature.compliment_editor.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ephirium.coffee.feature.compliment_editor.R.string
import com.ephirium.coffee.feature.compliment_editor.presentation.model.ComplimentEditorUiModel
import com.ephirium.coffee.preview.ThemePreview
import com.ephirium.coffee.theme.CoffeeTheme

internal data class ComplimentEditorActions(
    val onTextChanged: (String) -> Unit,
    val onLanguageCodeChanged: (String) -> Unit,
    val onPublish: () -> Unit,
)

@Composable
internal fun ComplimentEditor(
    modifier: Modifier = Modifier,
    complimentEditorUiModel: ComplimentEditorUiModel,
    complimentEditorActions: ComplimentEditorActions,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val enabled = complimentEditorUiModel.languageCode
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(listOf("ru", "en")) {
                InputChip(
                    selected = enabled == it,
                    onClick = {
                        complimentEditorActions.onLanguageCodeChanged(it)
                    },
                    label = {
                        Text(it)
                    },
                )
            }
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        TextField(
            value = complimentEditorUiModel.text,
            placeholder = {
                Text(
                    text = stringResource(string.type_your_compliment),
                    fontWeight = Bold,
                    style = typography.bodyMedium,
                )
            },
            onValueChange = complimentEditorActions.onTextChanged,
            textStyle = typography.bodyMedium.copy(fontWeight = SemiBold),
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(onClick = complimentEditorActions.onPublish) {
            Text(text = stringResource(string.publish))
        }
    }
}

private class ComplimentEditorUiModelProvider : PreviewParameterProvider<ComplimentEditorUiModel> {
    @Suppress("SpellCheckingInspection")
    override val values: Sequence<ComplimentEditorUiModel> = sequenceOf(
        ComplimentEditorUiModel.default,
        ComplimentEditorUiModel(text = "You are beautiful", languageCode = "en"),
        ComplimentEditorUiModel(text = "Ты такая красивая", languageCode = "ru"),
    )
}

@ThemePreview
@Composable
private fun ComplimentEditorPreview(@PreviewParameter(ComplimentEditorUiModelProvider::class) complimentEditorUiModel: ComplimentEditorUiModel) {
    CoffeeTheme {
        Surface(color = colorScheme.background) {
            ComplimentEditor(
                complimentEditorUiModel = complimentEditorUiModel,
                complimentEditorActions = ComplimentEditorActions(
                    onTextChanged = {},
                    onLanguageCodeChanged = {},
                    onPublish = {},
                ),
            )
        }
    }
}