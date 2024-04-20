package com.example.taxeazy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taxeazy.R

@Composable
fun TextComponent(value: String, fontStyletxt: FontStyle, fweight: FontWeight, size: Int, minheight: Int, colortxt: Color, alignment: TextAlign) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = minheight.dp),
        style = TextStyle(
            fontSize = size.sp,
            fontWeight = fweight,
            fontStyle = fontStyletxt
        ),
        color = colortxt,
        textAlign = alignment
    )
}
@Composable
fun GreyButtonComponent(value: String, colortxt: Color, onClick: () -> Unit = {}) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
//                            colorResource(id = R.color.colorGray),
//                            colorResource(id = R.color.colorGray),
                            colorResource(id = R.color.purple_700),
                            colorResource(id = R.color.purple_700)

                        )
                    ),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colortxt
            )
        }

    }
}

@Composable
fun CButton(
    onClick: () -> Unit = {},
    text: String,
) {
    // make this button also reusable
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF7C9A92)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {

        Text(
            text = text,
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(500),
                color = Color.White
            )
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CTextField(
    onValueChange: (String) -> Unit = {},
    hint: String,
    value: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        icon?.let {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = hint,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color(0xFF6B6B6B) // Darker hint color
                    )
                )
            },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Black, // Darker text color
                focusedIndicatorColor = Color(0xFF212121), // Darker outline color
                unfocusedIndicatorColor = Color(0xFF212121) // Darker outline color
            )
        )
    }
}

