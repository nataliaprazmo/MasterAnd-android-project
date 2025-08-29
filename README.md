# MasterAnd 🎮🎨

A mobile game built in **Android Studio** for my master's studies. MasterAnd is a modern take on the classic **Mastermind** game, where the player must guess a hidden sequence of **4 colors** in the smallest number of attempts possible.

[![Demo GIF](/demo/demo.gif)](https://res.cloudinary.com/dvaw6nzfv/video/upload/v1756469688/demo1_ju70xt.mp4)

_Click the GIF to watch the full video_

## Technologies

![Kotlin](https://img.shields.io/badge/Kotlin-blueviolet?style=for-the-badge&logo=kotlin&logoColor=white)
![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

## Features

-   **Login & profile**: set nickname, email, and optional avatar
-   **Color pool size**: choose **6–10** available colors to guess from (the secret sequence length is always **4**)
-   **No duplicates**: each color in the secret sequence is unique
-   **Guessing flow**:
    -   Start with **4 white circles**; tap each to cycle/draw a color from the chosen pool
    -   A color already used in the sequence won’t be drawn again for the other circles
    -   After selecting all 4 colors, a **Check** button appears
-   **Feedback pegs** (shown as 4 small circles next to the guess):
    -   ⚪ **White** — color not in the final sequence
    -   🟡 **Yellow** — color is in the final sequence, but wrong position
    -   🔴 **Red** — correct color in the correct position
-   **Highscore table**:
    -   Displays the **last obtained score**
    -   Lists users’ nicknames with their scores
    -   If a user logs in with the same **email** but a different **nickname**, the nickname is **overwritten** with the new one

## Installation / Deployment

1. Clone the repository:
    ```bash
    git clone https://github.com/nataliaprazmo/MasterAnd-android-project.git
    ```
2. Open the project in **Android Studio**

    - Launch Android Studio
    - Select **File → Open…**
    - Choose the `MasterAnd` project folder

3. Build & Sync

    - Let Gradle automatically download dependencies and build the project.
    - If prompted, install any missing SDK components.

4. **Run the app**

    - Select an **Android emulator** (API 26 or higher recommended)
    - Or connect a **physical Android device** with USB debugging enabled
    - Click ▶️ **Run** in Android Studio

5. The game will install and start automatically on your device/emulator.
