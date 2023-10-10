import type { EmitterSubscription } from 'react-native';
import type { Constants } from './config';

type IntentActionConfigTuple = [string, string][];
type IntentActionConfig = { action: string; datakey: string }[];

interface BroadcastEventData {
  [Constants.DATA_PROP]: string;
}
type BroadcastEventCallback = (d: BroadcastEventData) => void;

interface NativeModuleType {
  setIntentConfig(args: IntentActionConfigTuple): Promise<boolean>;
}

interface BroadcastReceiverInterface {
  setIntentActionConfig(args: IntentActionConfig): Promise<boolean>;
  addEventListener(cb: BroadcastEventCallback): EmitterSubscription;
}

export type {
  NativeModuleType,
  BroadcastReceiverInterface,
  IntentActionConfigTuple,
  IntentActionConfig,
  BroadcastEventData,
  BroadcastEventCallback,
};
