/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.ui.material.samples

import androidx.animation.transitionDefinition

import androidx.compose.Composable
import androidx.compose.emptyContent
import androidx.compose.remember
import androidx.compose.state
import androidx.ui.animation.ColorPropKey
import androidx.ui.animation.PxPropKey
import androidx.ui.animation.Transition
import androidx.ui.core.Alignment
import androidx.ui.core.DensityAmbient
import androidx.ui.core.Text
import androidx.ui.foundation.Border
import androidx.ui.foundation.Box
import androidx.ui.foundation.DrawBackground
import androidx.ui.foundation.DrawBorder
import androidx.ui.foundation.Icon
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutSize
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Stack
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Tab
import androidx.ui.material.TabRow
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Favorite
import androidx.ui.unit.dp
import androidx.ui.unit.toPx


@Composable
fun TextTabs() {
    var state by state { 0 }
    val titles = listOf("TAB 1", "TAB 2", "TAB 3 WITH LOTS OF TEXT")
    Column {
        TabRow(items = titles, selectedIndex = state) { index, text ->
            Tab(text = { Text(text) }, selected = state == index, onSelected = { state = index })
        }
        Text(
            modifier = LayoutGravity.Center,
            text = "Text tab ${state + 1} selected",
            style = MaterialTheme.typography().body1
        )
    }
}

@Composable
fun IconTabs() {
    var state by state { 0 }
    val icons = listOf(Icons.Filled.Favorite, Icons.Filled.Favorite, Icons.Filled.Favorite)
    Column {
        TabRow(items = icons, selectedIndex = state) { index, icon ->
            Tab(icon = { Icon(icon) }, selected = state == index, onSelected = { state = index })
        }
        Text(
            modifier = LayoutGravity.Center,
            text = "Icon tab ${state + 1} selected",
            style = MaterialTheme.typography().body1
        )
    }
}

@Composable
fun TextAndIconTabs() {
    var state by state { 0 }
    val titlesAndIcons = listOf(
        "TAB 1" to Icons.Filled.Favorite,
        "TAB 2" to Icons.Filled.Favorite,
        "TAB 3 WITH LOTS OF TEXT" to Icons.Filled.Favorite
    )
    Column {
        TabRow(items = titlesAndIcons, selectedIndex = state) { index, (title, icon) ->
            Tab(
                text = { Text(title) },
                icon = { Icon(icon) },
                selected = state == index,
                onSelected = { state = index }
            )
        }
        Text(
            modifier = LayoutGravity.Center,
            text = "Text and icon tab ${state + 1} selected",
            style = MaterialTheme.typography().body1
        )
    }
}

@Composable
fun ScrollingTextTabs() {
    var state by state { 0 }
    val titles = listOf(
        "TAB 1",
        "TAB 2",
        "TAB 3 WITH LOTS OF TEXT",
        "TAB 4",
        "TAB 5",
        "TAB 6 WITH LOTS OF TEXT",
        "TAB 7",
        "TAB 8",
        "TAB 9 WITH LOTS OF TEXT",
        "TAB 10"
    )
    Column {
        TabRow(items = titles, selectedIndex = state, scrollable = true) { index, text ->
            Tab(text = { Text(text) }, selected = state == index, onSelected = { state = index })
        }
        Text(
            modifier = LayoutGravity.Center,
            text = "Scrolling text tab ${state + 1} selected",
            style = MaterialTheme.typography().body1
        )
    }
}


@Composable
fun FancyTabs() {
    var state by state { 0 }
    val titles = listOf("TAB 1", "TAB 2", "TAB 3")
    Column {
        TabRow(items = titles, selectedIndex = state) { index, title ->
            FancyTab(title = title, onClick = { state = index }, selected = (index == state))
        }
        Text(
            modifier = LayoutGravity.Center,
            text = "Fancy tab ${state + 1} selected",
            style = MaterialTheme.typography().body1
        )
    }
}


@Composable
fun FancyIndicatorTabs() {
    var state by state { 0 }
    val titles = listOf("TAB 1", "TAB 2", "TAB 3")

    // Reuse the default transition, and provide our custom indicator as its child
    val indicatorContainer = @Composable { tabPositions: List<TabRow.TabPosition> ->
        TabRow.IndicatorContainer(tabPositions = tabPositions, selectedIndex = state) {
            FancyIndicator(Color.White)
        }
    }

    Column {
        TabRow(
            items = titles,
            selectedIndex = state,
            indicatorContainer = indicatorContainer
        ) { index, text ->
            Tab(text = { Text(text) }, selected = state == index, onSelected = { state = index })
        }
        Text(
            modifier = LayoutGravity.Center,
            text = "Fancy indicator tab ${state + 1} selected",
            style = MaterialTheme.typography().body1
        )
    }
}


@Composable
fun FancyIndicatorContainerTabs() {
    var state by state { 0 }
    val titles = listOf("TAB 1", "TAB 2", "TAB 3")

    val indicatorContainer = @Composable { tabPositions: List<TabRow.TabPosition> ->
        FancyIndicatorContainer(tabPositions = tabPositions, selectedIndex = state)
    }

    Column {
        TabRow(
            items = titles,
            selectedIndex = state,
            indicatorContainer = indicatorContainer
        ) { index, text ->
            Tab(text = { Text(text) }, selected = state == index, onSelected = { state = index })
        }
        Text(
            modifier = LayoutGravity.Center,
            text = "Fancy transition tab ${state + 1} selected",
            style = MaterialTheme.typography().body1
        )
    }
}

@Composable
fun ScrollingFancyIndicatorContainerTabs() {
    var state by state { 0 }
    val titles = listOf(
        "TAB 1",
        "TAB 2",
        "TAB 3 WITH LOTS OF TEXT",
        "TAB 4",
        "TAB 5",
        "TAB 6 WITH LOTS OF TEXT",
        "TAB 7",
        "TAB 8",
        "TAB 9 WITH LOTS OF TEXT",
        "TAB 10"
    )
    val indicatorContainer = @Composable { tabPositions: List<TabRow.TabPosition> ->
        FancyIndicatorContainer(tabPositions = tabPositions, selectedIndex = state)
    }

    Column {
        TabRow(
            items = titles,
            selectedIndex = state,
            indicatorContainer = indicatorContainer,
            scrollable = true
        ) { index, text ->
            Tab(text = { Text(text) }, selected = state == index, onSelected = { state = index })
        }
        Text(
            modifier = LayoutGravity.Center,
            text = "Scrolling fancy transition tab ${state + 1} selected",
            style = MaterialTheme.typography().body1
        )
    }
}


@Composable
fun FancyTab(title: String, onClick: () -> Unit, selected: Boolean) {
    Tab(selected, onClick) {
        Column(
            LayoutPadding(10.dp) + LayoutHeight(50.dp) + LayoutWidth.Fill,
            arrangement = Arrangement.SpaceBetween
        ) {
            Box(
                LayoutSize(10.dp) + LayoutGravity.Center + DrawBackground(
                    color = if (selected) Color.Red else Color.White
                ),
                children = emptyContent()
            )
            Text(
                text = title,
                style = MaterialTheme.typography().body1,
                modifier = LayoutGravity.Center
            )
        }
    }
}


@Composable
fun FancyIndicator(color: Color) {
    // Draws a rounded rectangular with border around the Tab, with a 5.dp padding from the edges
    // Color is passed in as a parameter [color]
    Stack(
        LayoutPadding(5.dp) + LayoutSize.Fill + DrawBorder(
            Border(2.dp, color),
            RoundedCornerShape(5.dp)
        )
    ) {}
}


@Composable
fun FancyIndicatorContainer(tabPositions: List<TabRow.TabPosition>, selectedIndex: Int) {
    val indicatorStart = remember { PxPropKey() }
    val indicatorEnd = remember { PxPropKey() }
    val indicatorColor = remember { ColorPropKey() }

    val colors = listOf(Color.Yellow, Color.Red, Color.Green)
    val transitionDefinition =
        remember(tabPositions) {
            transitionDefinition {
                tabPositions.forEachIndexed { index, position ->
                    state(index) {
                        this[indicatorStart] = position.left.toPx()
                        this[indicatorEnd] = position.right.toPx()
                        this[indicatorColor] = colors[index % colors.size]
                    }
                }
                repeat(tabPositions.size) { from ->
                    repeat(tabPositions.size) { to ->
                        if (from != to) {
                            transition(fromState = from, toState = to) {
                                // Handle directionality here, if we are moving to the right, we
                                // want the right side of the indicator to move faster, if we are
                                // moving to the left, we want the left side to move faster.
                                val startStiffness = if (from < to) 50f else 1000f
                                val endStiffness = if (from < to) 1000f else 50f
                                indicatorStart using physics {
                                    dampingRatio = 1f
                                    stiffness = startStiffness
                                }
                                indicatorEnd using physics {
                                    dampingRatio = 1f
                                    stiffness = endStiffness
                                }
                            }
                        }
                    }
                }
            }
        }

    // Fill up the entire TabRow with this container, and place children at the left so we can use
    // Padding to set the 'offset'
    Container(expanded = true, alignment = Alignment.BottomStart) {
        Transition(transitionDefinition, selectedIndex) { state ->
            val density = DensityAmbient.current
            val offset = with(density) { state[indicatorStart].toDp() }
            val width = with(density) {
                (state[indicatorEnd] - state[indicatorStart]).toDp()
            }
            Container(width = width, modifier = LayoutPadding(start = offset)) {
                // Pass the current color to the indicator
                FancyIndicator(state[indicatorColor])
            }
        }
    }
}
