package com.anas.foodmenu


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anas.foodmenu.data.DataSource
import com.anas.foodmenu.model.FoodMenu
import com.anas.foodmenu.model.food
import com.anas.foodmenu.ui.theme.FoodMenuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodMenuTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    FoodMenuApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodMenuApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection)
            )
    ) {
        Column {
            MenuTopBar()
            MenuList(
                menuList = DataSource().loadMenu(),
                modifier = Modifier
            )
        }
    }
}


@Composable
fun MenuCard(
    foodMenu: FoodMenu,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer
    )

    Card(
        //border = BorderStroke(1.dp, Color(0xFFF3CABD)),
        colors = CardDefaults.cardColors(
            //containerColor = Color(0xFFE1F5FE)
        ),
        modifier = Modifier
            .fillMaxWidth()


    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = color)
        ) {
            //main box

            Row {
                Image(
                    painter = painterResource(foodMenu.imageResourceId),
                    contentDescription = null,
                    modifier = Modifier
                        .height(80.dp)
                        .width(80.dp)
                        .padding(10.dp)
                        .clip(MaterialTheme.shapes.small),
                    contentScale = ContentScale.Crop

                )

                //main text

                Column {
                    Text(
                        text = LocalContext.current.getString(foodMenu.menuResourceId),
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier
                            .padding(top = 8.dp)

                    )
                    Text(
                        text = stringResource(foodMenu.rateResourceId),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(8.dp),
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                //desc button

                FoodDescButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
        if (expanded) {
            AboutFood(
                aboutFood = foodMenu.descResoucreId,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun MenuList(
    menuList: List<FoodMenu>,
    modifier: Modifier = Modifier,
) {


    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(menuList) { foodMenu ->
            MenuCard(
                foodMenu = foodMenu,
                modifier = Modifier
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun MenuTopBar(

) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Image(
                    painter = painterResource(R.drawable.icon_2),
                    contentDescription = "main_logo",

                    modifier = Modifier
                        .padding(8.dp)
                        .size(48.dp)
                        .clip(RectangleShape),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "FOOD MENU",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = Modifier
    )


}

@Composable
private fun FoodDescButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.desc1),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun AboutFood(
    @StringRes aboutFood: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
    ) {
        Text(
            text = stringResource(aboutFood),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(8.dp)
                .padding(start = 8.dp)
        )
    }
}

