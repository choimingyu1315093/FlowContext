package com.example.flowcontext.playground

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

//flow는 다른 컨텍스트로 옮길 수 없다.
//실행하면 에러 뜬다.
fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
fun simple(): Flow<Int> = flow {
    withContext(Dispatchers.Default){
        log("flow를 시작합니다.")
        for(i in 1..30){
            emit(i)
        }
    }
}

fun main(): Unit = runBlocking {
    launch (Dispatchers.IO){
        simple().collect {
            println(it)
        }
    }
}