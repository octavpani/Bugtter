document.addEventListener('DOMContentLoaded', function() {
    // const ta = new TextAnimation('.animate-title');
    // const ta2 = new TextAnimation('.animate-title2');
    const ta3 = new TextAnimation('.logo-title');



 });

class TextAnimation {
    constructor(el) {
        this.el = document.querySelector(el);
        this.chars = this.el.innerHTML.trim().split("");
        this.el.innerHTML = this._splitText();
        this.animate();
    }
    _splitText() {
        return this.chars.reduce((acc, curr) => {
            curr = curr.replace(/\s+/, '&nbsp;');
            return `${acc}<span class="char">${curr}</span>`;
        }, "");
    }
    animate() {
        this.el.classList.add('inview');
    }
}
