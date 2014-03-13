package controllers

import scala.io.Source
import org.apache.http.client.methods.HttpPost
import play.api.libs.json.Json
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient

object PutTweets {

  private val POST_URL = "http://localhost:9200/twitter/tweets/"

  def main(args: Array[String]): Unit = {
    var source = Source.fromFile("D:\\tmp\\abc.csv")
    val lines = source.getLines
    lines.foreach(post)
    source.close
  }

  def post(msg: String) = {
    val post = new HttpPost(POST_URL)
    val m = Json.obj(
      "message_ngram" -> msg)
    post.setEntity(new StringEntity(Json.stringify(m), "utf-8"))

    val client = new DefaultHttpClient
    val response = client.execute(post)
  }

}