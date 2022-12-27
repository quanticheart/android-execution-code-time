package com.quanticheart.executiontime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val duration = measureTimeMillis {
            arrayListOf<String>()
        }
    }
}

@OptIn(ExperimentalContracts::class)
public inline fun measureTimeMillis(block: () -> Unit): Long {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    val start = System.currentTimeMillis()
    block()
    val end = System.currentTimeMillis() - start
    println("Run in ${end.toTimeLabel()}.")
    return end
}

fun Long.toTimeLabel(): String {
    val minutes: Long = this / 1000 / 60
    val seconds: Long = this / 1000 % 60
    val milliseconds = "$minutes Min, $seconds Sec,"
    return "$milliseconds $this millis"
}