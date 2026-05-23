<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.ProjetoWeb">

        <!-- Tela de login -->
        <activity
            android:name="com.example.projetoweb.FormeLogin"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <!-- Tela de cadastro -->
        <activity
            android:name=".CadastroActivity"
            android:exported="false"/>

        <!-- Tela principal -->
        <activity
            android:name=".MainActivity"
            android:exported="false"/>

        <!-- Tela de agendamento -->
        <activity
            android:name=".AgendarActivity"
            android:exported="false"/>

        <!-- Tela de histórico -->
        <activity
            android:name=".HistoricoActivity"
            android:exported="false"/>

        <!-- Tela de perfil -->
        <activity
            android:name=".PerfilActivity"
            android:exported="false"/>

        <!-- Tela editar perfil -->
        <activity
            android:name=".EditarPerfilActivity"
            android:exported="false"/>

    </application>
</manifest>
