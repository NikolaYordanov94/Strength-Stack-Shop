// function fetchProductDetails(productId) {
//     fetch('http://localhost:8080/comments/view/' + productId)
//         .then(response => response.json())
//         .then(product => {
//             // Assuming 'product' has fields like 'createdAt' and 'comments'
//
//             // Create the product details HTML
//             let productDetailsHtml = `<h1>Product details</h1>
//                                       <p>${product.createdAt}</p>`;
//
//             // Create the comments HTML
//             let commentsHtml = product.comments.map(comment =>
//                 `<p>${comment.description}</p>`
//             ).join('');
//
//             // Combine everything
//             let combinedHtml = productDetailsHtml + commentsHtml;
//
//             // Insert into the appropriate part of your page
//             document.querySelector('.col-md-6').innerHTML = combinedHtml;
//         })
//         .catch(error => console.error('Error fetching product details:', error));
// }

//todo to finish fetching the comments