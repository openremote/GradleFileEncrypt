package com.cherryperry.gfe.providers

import org.gradle.api.provider.ProviderFactory

internal class EnvironmentPasswordProvider(
    private val providersFactory: ProviderFactory,
) : PasswordProvider {
    override val password: CharArray?
        get() = providersFactory.environmentVariable(ENVIRONMENT_KEY).orNull?.toCharArray()

    companion object {
        const val ENVIRONMENT_KEY = "GFE_PASSWORD"
    }
}
