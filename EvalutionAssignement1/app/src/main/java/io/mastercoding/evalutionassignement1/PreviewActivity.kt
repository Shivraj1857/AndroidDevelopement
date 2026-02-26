package io.mastercoding.evalutionassignement1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ExperimentalMaterial3Api


class PreviewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val note = intent.getStringExtra(MainActivity.EXTRA_NOTE) ?: ""
        val category = intent.getStringExtra(MainActivity.EXTRA_CATEGORY) ?: "Work"

        setContent {
            NotePreviewScreen(
                note = note,
                category = category,
                onShare = {
                    val text = if (note.isBlank()) "$category: (No text provided)" else "$category: $note"
                    shareText(text)
                }
            )
        }
    }

    private fun shareText(text: String) {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        startActivity(Intent.createChooser(sendIntent, "Share note via"))
    }
}

@Composable
fun NotePreviewScreen(
    note: String,
    category: String,
    onShare: () -> Unit
) {
    val (accent, surfaceTint, title) = when (category) {
        "Work" -> Triple(Color(0xFF1E88E5), Color(0xFFE3F2FD), "Work Note")
        "Personal" -> Triple(Color(0xFF43A047), Color(0xFFE8F5E9), "Personal Note")
        "Study" -> Triple(Color(0xFFE53935), Color(0xFFFFEBEE), "Study Note")
        else -> Triple(Color(0xFF607D8B), Color(0xFFECEFF1), "$category Note")
    }

    val colors = lightColorScheme(
        primary = accent,
        onPrimary = Color.White,
        background = Color(0xFFF4F6F8),
        surface = Color.White
    )

    MaterialTheme(colorScheme = colors) {
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { ShareBar(onShare) },
            containerColor = colors.background
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ElevatedCard(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .background(surfaceTint.copy(alpha = 0.25f))
                            .padding(20.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(18.dp)
                                    .background(accent, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color(0xFF1A1A1A)
                            )
                        }
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = if (note.isBlank()) "— (No text provided) —" else note,
                            fontSize = 16.sp,
                            color = Color(0xFF333333),
                            lineHeight = 22.sp
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    CenterAlignedTopAppBar(
        title = { Text("Note Preview") },
        navigationIcon = {
            IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}


@Composable
private fun ShareBar(onShare: () -> Unit) {
    Surface(shadowElevation = 8.dp) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = onShare,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Default.Share, contentDescription = "Share")
                Spacer(Modifier.width(8.dp))
                Text("Share Note")
            }
        }
    }
}