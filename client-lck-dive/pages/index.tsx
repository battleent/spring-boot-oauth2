import type { NextPage } from 'next'
import React, {
  useEffect,
  useState
} from "react";
import axios from 'axios'

const Home: NextPage = () => {

  const [me, setMe] = useState(undefined)

  useEffect(() => {
    const accessToken = window.localStorage.getItem('access_token')
    const url = 'http://localhost:8083/users/me'
    const config = {
      headers: {
        'Authorization': `Bearer ${accessToken}`
      }
    }

    axios.get(url, config)
      .then((result) => {
        setMe(result.data.username)
      })
      .catch((err) => {
        setMe(undefined)
        console.log(err)
      })
  })

  const logout = () => {
    window.localStorage.removeItem('access_token')
    setMe(undefined)
  }

  return (
    <div style={{ padding: "40px" }}>
      <h1>Client LCK Dive</h1>
      {!me &&
      <a href={"http://localhost:5000/oauth/authorize?client_id=lck-dive-client&redirect_uri=http://localhost:9092/oauth2/callback&response_type=code&scope=account"}>
        <button>로그인</button>
      </a>
      }
      {me &&
      <button onClick={logout}>{me} 로그아웃</button>
      }
    </div>
  )
}

export default Home
