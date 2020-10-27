import {Command, flags} from '@oclif/command'
import { getSubreddits } from '../service/reddit_service'
import axios from 'axios'
import cli from 'cli-ux'

export default class Reddit extends Command {
  static description = 'describe the command here'

  static examples = [
    `$ crawl reddit
hello world from ./src/hello.ts!
`,
  ]

  static flags = {
    help: flags.help({char: 'h'}),
    subreddits: flags.string({char: 's', description: 'subreddits list sepparated by semi collon ;'})
  }

  static args = [{name: 'subreddits'}]

  async run() {
    const {args, flags} = this.parse(Reddit)

    const subredditsList = flags.subreddits.split(';')
    cli.action.start(`Looking for results in reddit for: ${subredditsList}`)

    getSubreddits(subredditsList).then(axios.spread((...responses) => {
      for(let response of responses) {
        this.log('==================================================')
        formatTableData(response.data.data.children)
      }
    })).catch(errors=> {
        this.log(errors)
    })
  }
}

function formatTableData(data) {
  let result = data.flatMap(d => d.data).filter((value) => { return parseInt(value.score) >= 5000 })

  cli.table(result, {
    score: {
      header: 'SCORE'
    },
    subreddit: {
      header: 'SUBREDDIT'
    },
    title: {
      header: 'TITLE'
    },
    url: {
      header: 'LINK'
    },
    permalink : {
      header: 'COMMENTS'
    }
  })
}
