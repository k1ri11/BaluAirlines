package com.company.baluairlines.core.utils

/**
 * класс-обертка для обработки ответа от сервера
 * [Success] - ответ пришел без ошибки содержит только информацию
 * [Error] - ответ пришел с ошибкой, содержит строку с описанием ошибки, опцианальное поле data
 * [Loading] - состояние загрузки
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}