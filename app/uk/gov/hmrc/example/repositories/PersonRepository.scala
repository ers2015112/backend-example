/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.example.repositories

import uk.gov.hmrc.example.models.Person
import uk.gov.hmrc.mongo.MongoComponent
import uk.gov.hmrc.mongo.play.json.PlayMongoRepository

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class PersonRepository @Inject()(
                                  mongoComponent: MongoComponent
                                )(implicit ec: ExecutionContext
                                ) extends PlayMongoRepository[Person](
  mongoComponent = mongoComponent,
  collectionName = "person",
  domainFormat = Person.format,
  indexes = Seq.empty
) {
  def findAll(): Future[Seq[Person]] =
    collection.find().toFuture()

  def insert(person: Person): Future[Unit] =
    collection.insertOne(person).toFuture().map(_ => ())
}
