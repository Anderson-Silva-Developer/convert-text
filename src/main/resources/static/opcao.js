function changeImage() {
    const selectElement = document.getElementById('opcao');
    const selectedValue = selectElement.value;
    const imageElement = document.getElementById('seta');

    if (selectedValue === '1') {
        imageElement.src = './seta-direita.gif';
    } else if (selectedValue === '2') {
        imageElement.src = './seta-esquerda.gif';
    }
}