package com.example.flowcontext.playground

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

//flow는 다른 컨텍스트로 옮길 수 없다.
//단, flowOn을 사용하면 된다.
fun log2(msg: String) = println("[${Thread.currentThread().name}] $msg")
fun simple2(): Flow<Int> = flow {
    log("flow를 시작합니다.")
    for(i in 1..30){
        emit(i)
    }
}.flowOn(Dispatchers.Default) //flowOn을 기준으로 위쪽으로만 Dispatchers.Default가 적용된다.
    .map {
        it * 2
    }
fun main(): Unit = runBlocking {
    launch (Dispatchers.IO){
        simple2().collect {
            println(it)
        }
    }
}