package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import kotlinx.coroutines.launch

private val Purple = Color(0xFF281343)
private val IndicatorInactive = Color(0xFFE5E7EB)

private data class OnboardingPage(
    val image: Int,
    val title: String,
    val description: String
)

@Composable
fun OnboardingScreen(
    onFinished: () -> Unit
) {

    val pages = listOf(

        OnboardingPage(
            image = R.drawable.onboarding_1,
            title = "Аренда автомобилей",
            description =
                "Открой для себя удобный и доступный\nспособ передвижения"
        ),

        OnboardingPage(
            image = R.drawable.onboarding_2,
            title = "Безопасно и удобно",
            description =
                "Арендуй автомобиль и наслаждайся его\nудобством"
        ),

        OnboardingPage(
            image = R.drawable.onboarding_3,
            title = "Лучшие предложения",
            description =
                "Выбирай понравившееся среди сотен\nдоступных автомобилей"
        )
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            pages.size
        }
    )

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // Пропустить
        Text(
            text = "Пропустить",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = 80.dp,
                    end = 32.dp
                )
                .clickable {
                    onFinished()
                },
            color = Purple,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        // Слайды
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 115.dp)
        ) { page ->

            val item = pages[page]

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(
                        id = item.image
                    ),
                    contentDescription = item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(390.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(
                    modifier = Modifier.height(28.dp)
                )

                Text(
                    text = item.title,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                Text(
                    text = item.description,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp,
                    color = Color(0xFF555555),
                    lineHeight = 21.sp,
                    textAlign = TextAlign.Start
                )
            }
        }

        // Индикаторы
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = 64.dp,
                    bottom = 58.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            repeat(pages.size) { index ->

                Box(
                    modifier = Modifier
                        .width(
                            if (pagerState.currentPage == index) {
                                45.dp
                            } else {
                                20.dp
                            }
                        )
                        .height(8.dp)
                        .background(
                            color = if (
                                pagerState.currentPage == index
                            ) {
                                Purple
                            } else {
                                IndicatorInactive
                            },
                            shape = RoundedCornerShape(10.dp)
                        )
                )

                if (index != pages.lastIndex) {
                    Spacer(
                        modifier = Modifier.width(9.dp)
                    )
                }
            }
        }

        // Далее / Поехали
        Button(
            onClick = {

                if (pagerState.currentPage == pages.lastIndex) {

                    onFinished()

                } else {

                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1
                        )
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 50.dp,
                    bottom = 42.dp
                )
                .width(150.dp)
                .height(56.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple,
                contentColor = Color.White
            )
        ) {

            Text(
                text = if (
                    pagerState.currentPage == pages.lastIndex
                ) {
                    "Поехали"
                } else {
                    "Далее"
                },
                fontSize = 16.sp
            )
        }
    }
}