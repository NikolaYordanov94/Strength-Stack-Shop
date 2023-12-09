// let productId = document.getElementById('id').innerHTML;
// let loadButton = document.getElementById('showComment');
// let container = document.getElementById('comments')
//
// loadButton.addEventListener('click', loadComments);
//
// function loadComments() {
//     fetch('http://localhost:8080/product-details/'+ productId)
//         .then(response => response.json())
//         .then(json => json.forEach(currentProduct => {
//
//             let div = document.createElement('div');
//
//             let authorP = document.createElement('p');
//             let contentP = document.createElement('p');
//
//             authorP.textContent = currentProduct.comments;
//             contentP.textContent = comment.content;
//
//             div.appendChild(authorP);
//             div.appendChild(contentP);
//
//             container.appendChild(div);
//         }));
//
//     loadButton.style.display = 'none';
//
//     let form = document.getElementById('addComment');
//     form.style.display ='contents';
// }


//todo to finish fetching the comments