document.addEventListener("DOMContentLoaded", function() {
    let accessToken = localStorage.getItem('accessToken');
    const toURL = localStorage.getItem('toURL')


    if (!accessToken) {
        console.warn("No access token found. Redirecting to login...");
        window.location.href = "/admin/login";
        return;
    }

    fetch(toURL, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + accessToken
        }
    })
        .then(response => {
            if (response.status === 401) {
                console.warn("Unauthorized! Redirecting to login...");
                window.location.href = "/admin/login";
                return;
            }
            return response.text();
        })
        .then(html => {
            document.body.innerHTML = html;
        })
        .then(localStorage.removeItem('toURL'))
        .catch(error => console.error('Error:', error));
});

function loadPage(url, accessToken) {
    const loader = "/admin/loader"
    if (!accessToken) {
        console.error("No access token provided!");
        return;
    }

    localStorage.setItem('accessToken', accessToken);
    localStorage.setItem('toURL', url)

    setTimeout(() => {
        window.location.href = loader;
    }, 100);
}