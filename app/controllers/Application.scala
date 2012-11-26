package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {

  //Form作る
  val tweetForm = Form(
    "content" -> nonEmptyText(maxLength = 140)
  )

  //200返す感じかの？
  def index = Action {
    Ok(views.html.index(tweetForm, Tweet.all))
  }

  //パターンマッチっぽいことやってる
  def tweet = Action { implicit request =>
    tweetForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(errors, Tweet.all)),
      content => {
        Tweet.create(content)
        Redirect(routes.Application.index())
      }
    )
  }
}