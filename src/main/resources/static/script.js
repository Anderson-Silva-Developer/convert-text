document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('converterForm');
    const resultadoDiv = document.getElementById('resultado');
    const duration = document.getElementById('duration');
    const title = document.getElementById('title');
    const imgVideo = document.getElementById('imgVideo');

    form.addEventListener('submit', (event) => {
        event.preventDefault();

        fetch(form.action, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams(new FormData(form)).toString(),
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                duration.textContent = data.duration;
                title.textContent = data.title;
                imgVideo.src = data.imgVideo;
                resultadoDiv.style.display = 'block';
            })
            .catch(error => console.error('Error:', error));
    });
});
