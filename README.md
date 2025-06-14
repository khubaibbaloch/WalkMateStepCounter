# 🚶‍♂️ WalkMate

**WalkMate** is a lightweight and user-friendly step counter app that helps users track their daily walking activity, set goals, and stay motivated. Designed with Kotlin and Jetpack Compose, WalkMate runs efficiently in the background, keeping your fitness goals in check.

---

## ✨ Features

### 🧮 Step Counter
- Uses built-in accelerometer or step detector sensor to count steps accurately.
- Works offline and in the background.
- Battery-friendly implementation using foreground service or `JobScheduler`.

### 🎯 Daily Step Goal
- Allows users to set a custom daily step goal.
- Sends a **congratulatory notification** when the goal is reached.

### 📊 Stats & Progress
- Displays total steps, goal completion status, and live updates.
- Optionally shows calories burned and distance walked.

### 🛠️ Background Service
- Keeps tracking steps even if the app is closed (foreground service).
- Handles permission and sensor availability checks gracefully.

---

## 🧰 Tech Stack

- **Kotlin** & **Jetpack Compose** – Modern Android UI toolkit.
- **MVVM Architecture** – Clean separation of logic and UI.
- **Sensors API** – Uses `Sensor.TYPE_STEP_DETECTOR` or `TYPE_ACCELEROMETER`.
- **Foreground Service** – For persistent step tracking.
- **DataStore / SharedPreferences** – For saving step count and user goals.

---

## 🔒 Permissions Used

- `ACTIVITY_RECOGNITION` – To detect physical activity on Android Q+.
- `FOREGROUND_SERVICE` – To run the step counting service reliably.
- `WAKE_LOCK` – Optional, to ensure sensor stays active on low power devices.

---

## 🚀 How to Use

1. Clone the repository and open it in **Android Studio**.
2. Connect a physical device (step sensors may not work on emulators).
3. Grant the necessary permissions (especially `ACTIVITY_RECOGNITION`).
4. Set your daily goal and start walking!

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).  
You are free to use, modify, and distribute this app for personal or educational purposes. Commercial use is permitted under the terms of the license.

---

## 🙌 Credits

- Created by [@khubaibbaloch](https://github.com/yourusername)
- Created by [@ParvezMayar](https://github.com/yourusername)

---


