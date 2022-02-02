package com.example.sauexpert.survey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sauexpert.R
import com.example.sauexpert.profile.ProfileForInspection
import com.example.sauexpert.ui.theme.Gray50
import com.example.sauexpert.widgets.compose.MainButton

@Composable
fun HadsScreen() {
    val hadsSurveyList = listOf(
        Question(
            "1. Я испытываю напряжение, мне не по себе  ",
            listOf(
                "всё время",
                "часто",
                "время от времени, иногда",
                "совсем не испытываю",
            )
        ),
        Question(
            " 2. Я испытываю страх, кажется, что что-то ужасное может вот-вот случиться  ",
            listOf(
                "определенно это так, и страх очень велик ",
                "да, это так, но страх не очень велик ",
                "иногда, но это меня не беспокоит  ",
                "совсем не испытываю",
            )
        ),
        Question(
            "1. Я испытываю напряжение, мне не по себе  ",
            listOf(
                "всё время",
                "часто",
                "время от времени, иногда",
                "совсем не испытываю",
            )
        ),
        Question(
            "3. Беспокойные мысли крутятся у меня в голове ",
            listOf(
                "всё время",
                "часто",
                "время от времени, иногда",
                "совсем не испытываю",
            )
        ),
        Question(
            " 4. Я легко могу присесть и расслабиться ",
            listOf(
                "определенно, это так  ",
                "наверное, это так ",
                "лишь изредка, это так  ",
                "совсем не могу ",
            )
        ),
        Question(
            " 5. Я испытываю внутреннее напряжение или дрожь   ",
            listOf(
                "всё время",
                "часто",
                "время от времени, иногда",
                "совсем не испытываю",
            )
        ),
        Question(
            "6. Я испытываю неусидчивость, мне постоянно нужно двигаться  ",
            listOf(
                "определенно, это так  ",
                "наверное, это так ",
                "лишь изредка, это так  ",
                "совсем не могу ",
            )
        ),
        Question(
            "7. У меня бывает внезапное чувство паники ",
            listOf(
                "всё время",
                "часто",
                "время от времени, иногда",
                "совсем не испытываю",
            )
        ),
        Question(
            "8. То, что приносило мне большое удовольствие, и сейчас вызывает у меня такое же чувство   ",
            listOf(
                "всё время",
                "часто",
                "время от времени, иногда",
                "совсем не испытываю",
            )
        ),
        Question(
            "9. Я способен рассмеяться и увидеть в том или ином событии смешное  ",
            listOf(
                "определенно, это так  ",
                "наверное, это так ",
                "лишь изредка, это так  ",
                "совсем не могу ",
            )
        ),
        Question(
            "10. Я испытываю бодрость",
            listOf(
                "всё время",
                "часто",
                "время от времени, иногда",
                "совсем не испытываю",
            )
        ),
        Question(
            "11. Мне кажется, что я стал все делать очень медленно  ",
            listOf(
                "практически все время  ",
                "часто",
                "время от времени, иногда",
                "совсем нет",
            )
        ),
        Question(
            "12. Я не слежу за своей внешностью   ",
            listOf(
                "определенно, это так  ",
                "наверное, это так ",
                "лишь изредка, это так  ",
                "совсем не могу ",
            )
        ),
        Question(
            "13. Я считаю, что мои дела (занятия, увлечения) могут принести мне чувство удовлетворения   ",
            listOf(
                "точно так же, как и обычно ",
                "да, но не в той степени, как раньше ",
                "значительно меньше, чем обычно",
                "совсем так не считаю "
            )
        ),
        Question(
            "14. Я могу получить удовольствие от хорошей книги, радио- или телепрограммы   ",
            listOf(
                "часто",
                "иногда",
                "редко",
                "очень редко",
            )
        ),
    )
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7))
            .verticalScroll(rememberScrollState())
    ) {
        SurveyHeader(stringResource(id = R.string.hads_scale))
        Spacer(modifier = Modifier.padding(10.dp))
        Column(
            Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.complete_hads_survey),
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.padding(6.dp))
            hadsSurveyList.forEach { text ->
                SurveyGroup(title = text.question, radioOptions = text.answers)
            }
            Spacer(modifier = Modifier.padding(6.dp))
            MainButton(
                onClick = { /*TODO*/ },
                enableState = true,
                text = stringResource(id = R.string.proceed)
            )
            Spacer(modifier = Modifier.padding(9.dp))
            Text(
                text = stringResource(
                    id = R.string.you_complete_the_prediabetes_questionnaire
                ),
                fontSize = 13.sp,
                color = Gray50
            )
        }
    }
}

@Composable
fun SurveyHeader(text:String){
    Icon(
        imageVector = Icons.Rounded.ArrowBack,
        contentDescription = null,
        modifier = Modifier.padding(top = 26.dp, start = 16.dp)
    )
    Text(
        text = text,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 26.dp, start = 16.dp, bottom = 26.dp)
    )
    ProfileForInspection(content = "Zhanna Ahmetova", text = 0.3f)
}