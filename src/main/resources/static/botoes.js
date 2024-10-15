function copyText(elementId) {
    const textarea = document.getElementById(elementId);
    textarea.select();
    document.execCommand('copy');
}

async function pasteText(elementId) {
    try {
        const text = await navigator.clipboard.readText();
        const textarea = document.getElementById(elementId);
        textarea.value = text;
    } catch (err) {
        console.error('Failed to paste text: ', err);
    }
}

window.copyText = copyText;
window.pasteText = pasteText;