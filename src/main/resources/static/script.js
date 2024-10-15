document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('converterForm');
    const inputJson = document.getElementById('inputJson')

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
                inputJson.value = data.json;
            })
            .catch(error => console.error('Error:', error));
    });
});