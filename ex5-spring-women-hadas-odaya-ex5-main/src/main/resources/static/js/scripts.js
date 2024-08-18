document.addEventListener('DOMContentLoaded', () => {
    const welcomeText = document.getElementById('welcome-text');
    if (welcomeText) {
        welcomeText.style.opacity = 0;
        setTimeout(() => {
            welcomeText.style.transition = 'opacity 2s';
            welcomeText.style.opacity = 1;
        }, 500);
    }

    bindCartButtons();
});

