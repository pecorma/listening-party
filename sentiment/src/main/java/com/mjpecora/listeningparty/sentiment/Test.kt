package com.mjpecora.listeningparty.sentiment

import android.content.Context
import android.util.Log
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.text.nlclassifier.NLClassifier

class Test {

    fun invoke(context: Context, test: String) {
        val options = NLClassifier.NLClassifierOptions.builder()
            .setBaseOptions(BaseOptions.builder().useGpu().build())
            .build()

        val classifer = NLClassifier
            .createFromFile(context, "text_classification_v2.tflite")

        val categories = classifer.classify(test)

        categories.forEach {
            Log.d("TestClassification", "${it.label} : ${it.score}")
        }
    }

}