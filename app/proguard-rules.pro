# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class com.itextpdf.** { *; }
-dontwarn com.itextpdf.**
-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder
-keep class android.support.v7.widget.** { *; }
-keep class android.support.v7.widget.SearchView { *; }
-keep class java.lang.** { *; }
-keep class android.support.** { *; }
-dontwarn android.support.**
-keep class android.support.v7.widget.** { *; }
-keep class android.support.design.widget.** { *; }
-keep class my.package.name.CustomAdapter


# Hide warnings about references to newer platforms in the library
-dontwarn android.support.v7.**
# don't process support library
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }


-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
# For Google Play Services
-keep public class com.google.android.gms.ads.**{
   public *;
}

# For old ads classes
-keep public class com.google.ads.**{
   public *;
}