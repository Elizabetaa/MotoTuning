const infoList = document.getElementById('serviceList')
const searchBar = document.getElementById('searchInput')

const allInfo = [];

fetch("http://localhost:8080/service/informationApi").then(response => response.json()).then(data => {
    for (let album of data) {
        allInfo.push(album);
    }
})

console.log(allInfo);

searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();

    let filteredInformation = allInfo.filter(info => {
        console.log(info.make);
        return info.model.toLowerCase().includes(searchingCharacters)
            || info.make.toLowerCase().includes(searchingCharacters);
    });
    console.log(filteredInformation)
    displayInformation(filteredInformation);
})


const displayInformation = (information) => {
    infoList.innerHTML = information
        .map((i) => {
            return ` 
<div class="row" id="serviceList">
                <a href="/service/details/${i.id}" >
                    <div style="margin-top: 5%">
                        <div class="card m-5 border-dark" style="width: 18rem;border-radius: 0;">
                            <div class="card-body">
                                <img src="/images/ktm-logo.png" alt="make image" style="width: 100%; height: 60px">
                                <h5 class="card-title"  style="text-align: center">${i.make}</h5>
                                <br>
                                <h6 class="card-subtitle mb-2 text-muted" >${i.model} ${i.year}</h6>
                                <p class="card-text">${i.addedOn}</p>
                            </div>
                        </div>
                    </div>
                </a>
            </div>`
        })
        .join('');

}