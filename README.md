# KYB page generation flow
* Авторизация в S&S через `/resources/auth/login`. Header `Authorization: <Base 64 login:password>` - получаем authToken
* Для нового externalUserId создание applicantRequest - `POST /resources/accounts/-/applicantRequests` с `Authorization: <authToken>` и телом 
```{
	"applicant": {
		"email": "",
		"externalUserId": "<externalUserId>",
		"requiredIdDocs": {
			"country": null,
			"includedCountries": null,
			"excludedCountries": null,
			"docSets": [
			{
			   "idDocSetType": "COMPANY",
			   "types": [
			     "COMPANY_DOC"
			   ],
			   "subTypes": null,
			   "fields": [
			     {
			       "name": "companyName",
			       "required": false
			     },
			     {
			       "name": "registrationNumber",
			       "required": false
			     },
			     {
			       "name": "type",
			       "required": false
			     },
			     {
			       "name": "incorporatedOn",
			       "required": false
			     },
			     {
			       "name": "country",
			       "required": false
			     },
			     {
			       "name": "legalAddress",
			       "required": false
			     },
			     {
			       "name": "controlScheme",
			       "required": false
			     }
			   ],
			   "imageIds": null,
			   "mode": null
			 }
		       ]
		}
	}
}
```

3. Генерируем временный accessToken: 
```curl -X POST \
  'https://test-api.sumsub.com/resources/accessTokens?userId=n-t.io&externalUserId=<externalUserId>' \
  -H 'Accept: application/json' \
  -H 'Authorization: <authToken>'
  ```

4. Передаем externalUserId и accessToken в скрипт
```
"externalUserId": "<externalUserId>",
"accessToken": "<accessToken>"
```
