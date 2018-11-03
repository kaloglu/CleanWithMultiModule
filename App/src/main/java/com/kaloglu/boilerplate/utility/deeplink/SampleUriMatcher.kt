package com.kaloglu.boilerplate.utility.deeplink

import android.content.UriMatcher

import javax.inject.Inject

class SampleUriMatcher @Inject constructor() : UriMatcher(UriMatcher.NO_MATCH) {

    init {
        addUri(Authority.SAMPLE_AUTHORITY0, CodeUri.SAMPLE_CODE1, Path.SAMPLE_PATH0)
        addUri(Authority.SAMPLE_AUTHORITY0, CodeUri.SAMPLE_CODE0)
        addUri(Authority.SAMPLE_AUTHORITY1, CodeUri.SAMPLE_CODE2, Path.WILDCARD)
        addUri(Authority.SAMPLE_AUTHORITY1, CodeUri.SAMPLE_CODE2)
        addUri(Authority.SAMPLE_AUTHORITY2, CodeUri.SAMPLE_CODE3, Path.WILDCARD)
        addUri(Authority.SAMPLE_AUTHORITY2, CodeUri.SAMPLE_CODE3)
    }

    private fun addUri(authority: Authority, codeUri: CodeUri, path: Path? = null) {
        addURI(authority.authority, path?.path, codeUri.uriCode)
    }

    enum class Authority(val authority: String) {
        SAMPLE_AUTHORITY1("screen0"),
        SAMPLE_AUTHORITY0("screen1"),
        SAMPLE_AUTHORITY2("screen2"),
    }

    enum class Path(val path: String) {
        WILDCARD("*"),
        SAMPLE_PATH0("feed/*")
    }

    enum class CodeUri(val uriCode: Int) {
        SAMPLE_CODE0(0),
        SAMPLE_CODE1(1),
        SAMPLE_CODE2(2),
        SAMPLE_CODE3(3),
    }
}