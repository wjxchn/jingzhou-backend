### venue_linking <期刊ID，论文ID>

```{
{
"mid": "xxxx",
"aid": "yyyy"
}
```

example

```
{"mid": "5bf573b81c5a1dcdd96ec669", "aid": "5451a5c9e0cf0b02b5f3bd3c"}
```


where "mid" is MAG entity ID and "aid" is AMiner entity ID.

### venue （期刊）


| **Field Name** | **Field Type** | **Description** | **Example** |
| -------------- | -------------- | --------------- | ----------- |
| id             | string | venue id | 5bf574641c5a1dcdd96f817b |
| JournalId      | string | journal id | 137773608 |
| ConferenceId   | string | conference id |
| DisplayName    | string | venue name | Nature |
| NormalizedName | string | normalized venue name | nature |

### paper（MAG/Aminer各种论文）

| **Field Name** | **Field** | **Description** | **Example**|
| ------------- | --------- | --------------- | ----------- | -------- |
|id      | string      | paper ID          | 53e9ab9eb7602d970354a97e|
|title              | string      | paper title       | Data mining: concepts and techniques|
| authors.name   | string    | author name |  Jiawei Han|
| author.org     | string   | author   | Department of Computer Science, University of Illinois at  affiliation  Urbana-Champaign|
| author.id      | string    | author ID                   | 53f42f36dabfaedce54dcd0c|
| venue.id       | string    | paper venue ID              | 53e17f5b20f7dfbc07e8ac6e|
| venue.raw      | string    paper venue                   Inteligencia Artificial, Revista Iberoamericana de| name     | Inteligencia Artificial|
|year             |int         | published year             | 2000|
| keywords       | list of   strings   | keywords |["data mining", "structured data", "world wide web", "social network", "relational data"]|
| citation   | int  | citation number  |   40829|
| page_start    | string    | page start           | 11|
| page_end      | string    | page end             |18|
| doc_type      | string    | paper type: journal, book, title…|book|
|lang           | string    | detected  language |  en|
| publisher     | string    | publisher     | Elsevier|
| volume        | string    | volume|   10|
|issue          | string    |issue|29|
|issn           | string    | issn|0020-7136|
|isbn           | string    |isbn|1-55860-489-8|
|doi            | string    |doi| 10.4114/ia.v10i29.873|
|pdf    | string |pdf URL|//s39/53e9ab9eb7602d970354a97e.pdf |
|url   |    list       | external links   | { "http://dx.doi.org", " http://xxxxx" }|
| abstract   | string   | abstract         | Our ability to generate…|

### author（论文作者）

| **Field Name** | **Field Type** | **Description** | **Example** |
| -------------- | -------------- | --------------- | ----------- |
|id|string|author id| 53f42f36dabfaedce54dcd0c|
|name|string|author name| Jiawei Han|
|normalized_name |  string| normalized author name   | jiawei han|
|orgs| list of strings   | author affiliations|["Department of Computer Science, University of Illinois  at Urbana-Champaign"] |
|org|string| last known affiliation   | Department of Computer Science, University of Illinois at Urbana-Champaign|
| position|string   | author position|professor|
|pubs.i |int|the number of author       |1217  publications|
|citation|int| author citation count    |191526|
|index|int| author h-index|175|
|tags.t|string| research interests       | "data mining"|
|tags.w|int| weight of interests|243|
|pubs.i|string| author paper id| 53e9b9fbb7602d97045f7bb8|




