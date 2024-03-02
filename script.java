<script>
let secretNumber;
let minNumber;
let maxNumber;
let attempts;
let score = 0;
let guessedCorrectly = false; // Variable to track whether the correct number has been guessed
let stickmanArt = [
    "  O  ",
    " /|\\ ",
    " / \\ "
];

function displayStickman() {
    let stickmanDiv = document.getElementById("stickmanPre");
    stickmanDiv.innerText = stickmanArt.join("\n");
}

function updateStickman() {
    let stickmanDiv = document.getElementById("stickmanPre");
    stickmanDiv.innerText = stickmanArt.slice(0, 3 - attempts).join("\n");
}

function initializeGame(difficulty) {
    switch (difficulty) {
        case "easy":
            minNumber = 1;
            maxNumber = 5;
            attempts = 3;
            break;
        case "medium":
            minNumber = 1;
            maxNumber = 10;
            attempts = 3;
            break;
        case "hard":
            minNumber = 1;
            maxNumber = 15;
            attempts = 3;
            break;
        default:
            minNumber = 1;
            maxNumber = 100; // Default to easy
            attempts = 3;
    }
    secretNumber = Math.floor(Math.random() * (maxNumber - minNumber + 1)) + minNumber;
    updateScoreboard();
}

function startGame(difficulty) {
    guessedCorrectly = false; // Reset guessedCorrectly when starting a new game
    initializeGame(difficulty);
    document.getElementById("startPage").style.display = "none";
    document.getElementById("gamePage").style.display = "block";
    document.getElementById("guessInput").disabled = false;
    document.getElementById("result").innerText = "";
    document.getElementById("nextGuessBtn").style.display = "none";
    document.getElementById("attempts").style.display = "block"; // Show attempts on game page
    displayStickman();
}

function updateScoreboard() {
    document.getElementById("score").innerText = `Score: ${score}`;
    document.getElementById("attempts").innerText = `Attempts left: ${attempts}`;
}

function checkGuess() {
    let guess = parseInt(document.getElementById("guessInput").value);
    if (isNaN(guess) || guess < minNumber || guess > maxNumber) {
        document.getElementById("result").innerText = `Please enter a valid number between ${minNumber} and ${maxNumber}.`;
    } else {
        if (guess === secretNumber) {
            if (!guessedCorrectly) {
                score++; // Increment score only if the guess is correct and it hasn't been guessed before
                guessedCorrectly = true;
            }
            document.getElementById("result").innerText = `Congratulations! You've guessed the number ${secretNumber} correctly! Your score is ${score}.`;
            document.getElementById("guessInput").disabled = true;
            document.getElementById("nextGuessBtn").style.display = "inline-block";
        } else {
            document.getElementById("result").innerText = "Incorrect guess. Try again!";
            attempts--;
            updateStickman();
        }

        updateScoreboard();

        if (attempts === 0) {
            document.getElementById("result").innerText = `Game Over! The secret number was ${secretNumber}.`;
            document.getElementById("guessInput").disabled = true;
            document.getElementById("nextGuessBtn").style.display = "inline-block";
            score = 0; // Reset the score
            document.getElementById("score").innerText = `Score: ${score}`; // Update the score display
            alert("You have lost all attempts. Please try again."); // Display pop-up message
            document.getElementById("guessInput").value = ""; // Clear input box
            document.getElementById("startPage").style.display = "block"; // Show start page
            document.getElementById("gamePage").style.display = "none"; // Hide game page
            document.getElementById("attempts").style.display = "none"; // Hide attempts on game page
            attempts = 3; // Reset attempts
        }
    }
}

function nextGuess() {
    document.getElementById("gamePage").style.display = "none";
    document.getElementById("startPage").style.display = "block";
    document.getElementById("guessInput").value = "";
    document.getElementById("nextGuessBtn").style.display = "none";
}
</script>