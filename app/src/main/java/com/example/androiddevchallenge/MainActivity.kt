/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Female
import androidx.compose.material.icons.outlined.Male
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

data class Puppy(
    val name: String,
    val breed: String,
    val description: String,
    val gender: String,
    val location: String,
    val filename: Int,
    val age: Int = 8
)

val puppies =
    listOf(
        Puppy(
            name = "Costello",
            breed = "Chihuahua",
            filename = R.raw.costello,
            gender = "Male",
            location = "Los Banos, CA",
            description = "Costello is a very friendly and handsome dog that needs a home. He is good with people, children, and other dogs but will chase cats. Costello prefers to be a housedog. If you leave him out he will try to escape. He should go to a home where there is at least one other friendly dog."
        ),
        Puppy(
            name = "Princess",
            breed = "German Shepherd",
            filename = R.raw.princess,
            gender = "Female",
            location = "Lathrop, CA",
            description = "Princess is about 6 to 7 months old. She was taken in from a person with too many dogs. She is a bit on the shy side at first, but she is very sweet once she is comfortable. She is a beautiful young girl who needs a chance to blossom."
        ),
        Puppy(
            name = "Bronson",
            breed = "Siberian Husky",
            filename = R.raw.bronson,
            gender = "Male",
            location = "Lindsay, CA",
            description = "Bronson is a stunning husky pup with piercing blue eyes. He is going to be a large adult. We will require breed experience for his adoption."
        ),
        Puppy(
            name = "Finlay",
            breed = "Labrador Retriever",
            filename = R.raw.finlay,
            gender = "Male",
            location = "Modesto, CA",
            description = "Finlay is a Brown lab puppy and very active. He is good with people. He may need introductions with other dogs, but is usually good with friendly dogs. We avoid Finlay around dogs that may not have friendly, and calm or playful personalities. Finlay has had some training."
        ),
        Puppy(
            name = "Panama",
            breed = "Pit Bull",
            filename = R.raw.panama,
            gender = "Female",
            location = "Visalia, CA",
            description = "Great with kids, dogs & cats. Potty training/crate training in progress, doing great! Rides well in the car. She loves to play with other dogs! Fast learner, treat & toy motivated. Super sweet, loving, social, friendly & playful."
        ),
        Puppy(
            name = "Charles",
            breed = "Border Collie",
            filename = R.raw.charles,
            gender = "Male",
            location = "Lindsay, CA",
            description = "Charles is a very soft herding mix puppy. He will do best in a home with another dog that can help him gain confidence."
        ),
        Puppy(
            name = "Bella",
            breed = "Cairn Terrier",
            filename = R.raw.bella,
            gender = "Female",
            location = "Parlier, CA",
            description = "Bella Skye is a friendly girl and would love to find a home with plenty of room to run. She does enjoy playing with other dogs, this little lady is all fun and games. She loves toys, especially squeaky toys she is sweet, cuddly, hilarious and loves to give kisses. There is nothing Bella Skye likes more than a nice belly rub!"
        ),
        Puppy(
            name = "Scout",
            breed = "Cattle Dog",
            filename = R.raw.scout,
            gender = "Male",
            location = "Fresno, CA",
            description = "Meet Scout! Approximately 3 months old and probably a australian cattle dog. Loving and smart."
        ),
        Puppy(
            name = "Rossi",
            breed = "Staffordshire Terrier",
            filename = R.raw.rossi,
            gender = "Female",
            location = "Cantua Creek, CA",
            description = "She loves people and plays with the other dogs in her foster home. Just like any puppy she is learning the basics, 'no bite', 'sit', 'bring me the toy'. She is a sweet girl and eager to learn and please. Puppies take a lot of time and patience, and Rossi is no different. She has puppy energy that could wear down a 3rd grade classroom so lots and lots of playtime is needed."
        ),
        Puppy(
            name = "Courage",
            breed = "Golden Retriever",
            filename = R.raw.courage,
            gender = "Male",
            location = "Clovis, CA",
            description = "Courage is just a puppy. However he will be a big boy once full grown. We believe him to be a Golden retriever mix . He needs a understanding family with lots of time and patience. A family who will take him to training classes and help him be a strong confident dog. Courage needs a home with no small children, however older well mannered children is ok. He is good with dogs big and small with proper introduction."
        )
    )

@Composable
fun MyApp() {
    val navController = rememberNavController()
    // for retaining scroll position on nav change
    val listState = rememberLazyListState()
    val favorites = remember {
        mutableStateListOf<Int>()
    }
    val toggleFavorite = { index: Int ->
        if (favorites.contains(index)) {
            favorites.remove(index)
        } else {
            favorites.add(index)
        }
    }
    val isFavorite = { index: Int ->
        favorites.contains(index)
    }
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(
                navController,
                listState,
                isFavorite,
                toggleFavorite
            )
        }
        composable("detail/{index}") { backStackEntry ->
            Detail(
                navController,
                backStackEntry.arguments?.getString("index")!!.toInt(),
                isFavorite,
                toggleFavorite
            )
        }
    }
}

// Start building your app here!
@Composable
fun Home(
    navController: NavController,
    listState: LazyListState,
    isFavorite: (Int) -> Boolean,
    toggleFavorite: (Int) -> Boolean
) {
    Scaffold(
        topBar = {
            Row(modifier = Modifier.padding(18.dp)) {
                Column {
                    Surface(contentColor = Color(224, 167, 31)) {
                        Icon(
                            imageVector = Icons.Filled.Pets,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(
                        "Adopt your next\nbest friend",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight(800),
                        modifier = Modifier.padding(vertical = 12.dp),
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            Modifier
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .shadow(24.dp)
                .background(MaterialTheme.colors.secondary)
                .padding(innerPadding)
                .padding(start = 18.dp, end = 18.dp),
            state = listState
        ) {
            itemsIndexed(puppies) { index, puppy ->
                Spacer(modifier = Modifier.height(18.dp))
                Card(
                    backgroundColor = MaterialTheme.colors.background,
                    elevation = 16.dp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .clickable {
                            navController.navigate(route = "detail/$index")
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = puppy.filename),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(12.dp)
                                )
                                .width(100.dp)
                                .height(110.dp)
                        )
                        Column(
                            Modifier
                                .padding(start = 12.dp)
                                .weight(1f)
                        ) {
                            Text(
                                puppy.name,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight(800)
                            )
                            Text(puppy.breed)
                            Text(
                                "${puppy.gender}, ${puppy.age} months old",
                                color = MaterialTheme.colors.onSecondary,
                                fontSize = 14.sp
                            )
                            Location(
                                modifier = Modifier.padding(top = 12.dp),
                                location = puppy.location,
                                fontSize = 14.sp
                            )
                        }
                        FavoriteIcon(
                            isFavorite = isFavorite(index),
                            toggleFavorite = { toggleFavorite(index) },
                            modifier = Modifier.offset(x = (12).dp, y = (-12).dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Detail(
    navController: NavController,
    index: Int,
    isFavorite: (Int) -> Boolean,
    toggleFavorite: (Int) -> Boolean
) {
    val puppy = remember { puppies[index] }
    Scaffold(
        topBar = {
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { navController.navigate(route = "home") }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
                FavoriteIcon(
                    isFavorite = isFavorite(index),
                    toggleFavorite = { toggleFavorite(index) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    puppy.name,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight(800)
                )
                Surface(contentColor = MaterialTheme.colors.onSurface) {
                    Icon(
                        imageVector = if (puppy.gender == "Male") Icons.Outlined.Male else Icons.Outlined.Female,
                        contentDescription = null,
                        modifier = Modifier.scale(1.2f)
                    )
                }
            }
            Row(modifier = Modifier.padding(vertical = 6.dp)) {
                Text(puppy.breed, modifier = Modifier.weight(1f))
                Text(
                    "${puppy.age} months old",
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
            Location(
                location = puppy.location,
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Box(modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = puppy.filename),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .shadow(16.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .weight(1f)
            ) {
                Text("About", style = MaterialTheme.typography.h6, fontWeight = FontWeight(800))
                Text(
                    puppy.description,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSecondary
                )
            }
            Row(
                modifier = Modifier
                    .offset(x = 18.dp, y = 18.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp))
                    .clickable { }
                    .background(Color.Red)
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .align(Alignment.End)
            ) {
                Surface(contentColor = Color.White, color = Color.Transparent) {
                    Icon(imageVector = Icons.Outlined.Pets, contentDescription = null)
                }
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    "ADOPT",
                    style = MaterialTheme.typography.h6,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun Location(location: String, fontSize: TextUnit, modifier: Modifier = Modifier) {
    Surface(
        contentColor = Color.Red,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 3.dp)
                    .offset(x = (-3).dp)
            )
            Text(location, color = MaterialTheme.colors.onSecondary, fontSize = fontSize)
        }
    }
}

@Composable
fun FavoriteIcon(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    toggleFavorite: () -> Unit
) {
    IconButton(
        onClick = { toggleFavorite() },
        modifier = modifier
            .wrapContentWidth(Alignment.End)
    ) {
        Surface(contentColor = if (isFavorite) Color.Red else MaterialTheme.colors.onSurface) {
            Icon(
                imageVector = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = null,
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
