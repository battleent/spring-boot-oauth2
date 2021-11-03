import type { NextPage } from 'next'
import { useRouter } from 'next/router'
import axios from 'axios'
import React, { useEffect } from "react";

const Callback: NextPage = () => {
  const router = useRouter()
  const code = router.query.code

  useEffect(() => {
    if (code !== undefined) {
      const url = `http://localhost:8081/oauth2/callback?code=${code}`
      axios.get(url)
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
