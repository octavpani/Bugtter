
document.addEventListener('DOMContentLoaded', function() {
	const btn = document.querySelector('#ud-btn');
	const  title = document.querySelector('#title').value
	const  content = document.querySelector('#content').value;
	const  sta = document.querySelector('#sta').value;
	const  urg = document.querySelector('#urg').value;

	console.log(sta);
	console.log(urg);
	console.log(content);
	btn.addEventListener('click', alertRepo(title, content, sta, urg));
 });




	function alertRepo(first, second, third, fourth) {
	if (!first || !second || !third || !fourth) {
		alert('入力の値が不正です。');
		console.log('hello');
		}
}

