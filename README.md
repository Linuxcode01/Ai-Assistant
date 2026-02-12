# Phone AI Assistant (Kotlin + Spring Boot)

Ye project tumhare phone assistant idea ka starter architecture deta hai:

- Android (Kotlin, Android Studio): Wake word, STT, TTS, phone controls, foreground background service.
- Spring Boot API (Kotlin): Command understanding, response generation, memory, history analytics, future heavy processing integration.

## High-Level Flow

1. Foreground service wake-word listener chalata hai.
2. Wake word trigger hone ke baad STT user command text banata hai.
3. Android app command ko Spring Boot API ko bhejta hai.
4. API NLP intent map karta hai aur response/action-plan banata hai.
5. Android app action-plan execute karta hai (call, flashlight, volume, app open).
6. Reply ko TTS se बोलता hai.
7. Saari interactions history aur analytics store me jati hain.

## Backend module

Path: `backend/`

Run (if Gradle installed):

```bash
cd backend
gradle bootRun
```

Main endpoints:

- `POST /api/v1/assistant/command` - command parsing + response generation.
- `POST /api/v1/memory/preferences` - user preference store.
- `GET /api/v1/memory/preferences/{userId}` - user preferences read.
- `GET /api/v1/history/{userId}` - command history.
- `GET /api/v1/history/analytics/overview` - top intents + totals.

## Android module notes

Path: `android/app/src/main/java/com/example/phoneassistant/`

Included starter classes:

- `AssistantForegroundService` - background continuous listening architecture.
- `VoicePipeline` - wake word + STT + TTS abstraction.
- `AssistantApiClient` - backend integration.
- `PhoneControlExecutor` - action plan execution layer.

## Future-proof Heavy Processing Ideas

- LLM orchestration service + prompt guardrails.
- Queue based async processing (Kafka/RabbitMQ).
- Vector memory (user preference + context retrieval).
- Voice biometrics and personalization.
- On-device/off-device hybrid intent routing.
