import axios from 'axios'

export function getSubreddits(themes) {
    let requests = [];
    for(let subreddit of themes) {
      console.log(`Fetching ${subreddit}...`)
      requests.push(axios.get(`https://www.reddit.com/r/${subreddit}.json?t=day`))
    }

    return axios.all(requests)
    
}