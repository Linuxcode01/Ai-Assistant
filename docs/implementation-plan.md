# Implementation Plan (Use-case Driven)

## Core Use Cases

1. **UC-1: Wake word se assistant activate karna**
   - Actor: User
   - Input: Wake word audio
   - Output: Listening mode enabled

2. **UC-2: Voice command execute karna**
   - Actor: User
   - Input: Natural language command (Hindi/English)
   - Output: Action + spoken confirmation

3. **UC-3: Preference based response**
   - Actor: System
   - Input: UserId
   - Output: Personalized language/tone

4. **UC-4: History & analytics**
   - Actor: Admin/Product layer
   - Input: Interaction logs
   - Output: Top intents, usage trends

## Suggested NLP Intent Mapping Strategy

- Step 1: Rule-based fast-path for critical commands (call, volume, torch, open app).
- Step 2: ML/LLM fallback for complex queries.
- Step 3: Confidence thresholding with clarification prompts.

## Android Permissions (expected)

- `RECORD_AUDIO`
- `FOREGROUND_SERVICE`
- `CALL_PHONE` (or safer `ACTION_DIAL`)
- `CAMERA` (for torch control)
- `QUERY_ALL_PACKAGES` (if generic app launching required)

## Spring Boot Responsibilities

- Intent classification endpoint
- Response generation endpoint/service
- User memory service
- History ingestion + analytics API
- Future AI model gateway abstraction
