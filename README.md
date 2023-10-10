# react-native-broadcast-receiver

Native module for handle android broadcast

## Installation

```sh
npm install react-native-handle-broadcast
```

### Usage

#### 1. Configure AndroidManifest.xml

```xml
<receiver android:name="com.handlebroadcast.BroadcastReceiverReceiver" android:enabled="true" android:exported="true">
  <intent-filter android:priority="1000">
    <action android:name="com.zzzz.yyyy.action" />
    <category android:name="android.intent.category.DEFAULT"></category>
  </intent-filter>
</receiver>
```

#### 2. Listen to broadcast events `(with custom intent actions and extraDataKey)`

```js
import { BroadcastReceiver } from 'react-native-handle-broadcast';

BroadcastReceiver.setIntentActionConfig([
  { action: 'com.zzzz.yyyy.action', datakey: '<data_key>' },
]);

React.useEffect(() => {
  const sub = BroadcastReceiver.addEventListener((data) =>
    console.log(data);
  );
  return () => sub.remove();
}, []);
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
