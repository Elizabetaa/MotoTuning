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
                <div  >
                    <div style="margin-top: 5%">
                        <div class="card m-5 border-dark" style="width: 18rem;border-radius: 0;">
                            <div class="card-body">
                                <img src="${i.image}" alt="make image" style="width: 100%; height: 60px">
                                <h5 class="card-title"  style="text-align: center">${i.make}</h5>
                                <br>
                                <div class="row" >
                                            <h6 style="margin-top: 12px;margin-left: 5%;" class="card-subtitle mb-2 text-muted" >${i.model}</h6>
                                            <a href= ${i.prdFurl}    target="_blank" >
                                                <img id="pdf" src="/images/download.png" alt="make image" width="80" height="50">
                                            </a>
                                        </div>
                                <p class="card-text">${i.addedOn}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>`
        })
        .join('');

}
