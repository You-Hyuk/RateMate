package com.example.ratemate.survey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// 결과 화면
@Composable
fun SurveyResultScreen(title: String, questions: List<QuestionItem>) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "설문조사 결과",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "제목: $title",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            questions.forEach { questionItem ->
                SurveyResult(questionItem)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@Composable
fun SurveyResult(question: QuestionItem) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = "질문: ${question.question}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        val answerCounts = remember { calculateAnswerCounts(question.answers) }

        answerCounts.forEach { (answer, count) ->
            Text(
                text = "$answer: $count",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
            )
        }
    }
}

fun calculateAnswerCounts(answers: List<String>): Map<String, Int> {
    return answers.groupingBy { it }.eachCount()
}

@Preview(showBackground = true)
@Composable
fun PreviewSurveyResultScreen() {
    val sampleQuestions = listOf(
        QuestionItem("질문1", mutableListOf("답변1-1", "답변1-2", "답변1-2", "답변1-1", "답변1-2", "답변1-3", "답변1-1", "답변1-1", "답변1-3"), "single"),
        QuestionItem("질문2", mutableListOf("답변2-1", "답변2-2", "답변2-3", "답변2-4", "답변2-5"), "multiple"),
        QuestionItem("질문3", mutableListOf("답변3-1", "답변3-2", "답변3-3", "답변3-4", "답변3-5", "답변3-6"), "single"),
        QuestionItem("질문4", mutableListOf("답변4-1", "답변4-2", "답변4-3", "답변4-4"), "multiple")
    )
    SurveyResultScreen("사용자 만족도 조사", sampleQuestions)
}