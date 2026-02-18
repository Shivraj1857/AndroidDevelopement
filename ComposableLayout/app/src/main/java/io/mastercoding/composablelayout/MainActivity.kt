package io.mastercoding.composablelayout

import android.media.Image
import android.os.Bundle
import android.print.PrintAttributes
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.mastercoding.composablelayout.ui.theme.ComposableLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
                circularImage()
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
private fun previewfunction() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Hello",
            color = Color.White,
            modifier = Modifier
                .background(Color.Blue)
                .size(200.dp)
                .padding(20.dp)
                .border(5.dp, Color.Red)
                .clip(CircleShape)
                .background(Color.Yellow)
                .clickable { }
        )
    }

//    Column() {
//        ListView(R.drawable.img1,"Shiva","Master")
//        ListView(R.drawable.img2,"meet","MasterPro")
//    }

}

@Composable
fun ListView(id: Int, Name: String, Desiganation: String) {
    Row(Modifier.padding(8.dp)) {
        Image(painter = painterResource(id), contentDescription = "")
        Modifier.size(40.dp)

        Column {
            Text(text = Name, fontWeight = FontWeight.Bold)
            Text(text = Desiganation, fontWeight = FontWeight.Thin)
        }
    }
}

@Composable
fun circularImage() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.img),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(2.dp, Color.LightGray), contentDescription = ""
        )
    }
}



