import type { NextPage } from 'next'
import { useRouter } from 'next/router'
import axios from 'axios'
import React, { useEffect } from "react";

const Callback: NextPage = () => {
  const router = useRouter()
  const code = router.query.code

  useEffect(() => {
    if (code !== undefined) {
      const url = 'http://localhost:5000/oauth/token'
      const config = {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Authorization': 'Basic Y2xpZW50MjpwYXNz'
        }
      }
      const params = new URLSearchParams()
      params.append('code', code as string)
      params.append('grant_type', 'authorization_code')
      params.append('redirect_uri', 'http://localhost:9092/oauth2/callback')

      axios.post(url, params, config)
        .then((result) => {
          window.localStorage.setItem('access_token', result.data.access_token)
          router.replace('/')
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }, [code])

  return (
    <div></div>
  )
}

export default Callback
