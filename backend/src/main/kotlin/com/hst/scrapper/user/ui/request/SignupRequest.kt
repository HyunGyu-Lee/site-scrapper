package com.hst.scrapper.user.ui.request

/**
 * Copyright 2021 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author hyungyu.lee@nhn.com
 * @date 2021-09-30
 */
data class SignupRequest(
    val loginId: String,
    val loginPassword: String,
    val email: String,
    val name: String
)