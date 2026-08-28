# TaskNest

Android-застосунок на Kotlin і Jetpack Compose (Material 3): список завдань (ToDo), калькулятор на чотири операції та розминкові екрани (привітання з Toast, розрахунок віку, місяць/день тижня зі списком свят).

## Стек
- Kotlin, Jetpack Compose (Material 3)
- Android SDK 35, minSdk 26
- SharedPreferences для збереження ToDo

## Структура
```
app/src/main/java/com/mtkach/tasknest/
  MainActivity.kt
  data/            # TodoItem, TodoStore
  util/            # Calculator, DateLab
  ui/
    TaskNestApp.kt, Nav.kt
    screens/       # Hub, Todo, Calculator, Greeting, Age, MonthDay
    components/    # AppScaffold
    theme/
```

## Запуск
```bash
./gradlew assembleDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.mtkach.tasknest/.MainActivity
```
