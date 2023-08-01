import type { IntentActionConfig } from './types';

const defaultConfig: IntentActionConfig = [];

type Event = 'BROADCAST_EVENT';
const BROADCAST_EVENT_NAME: Event = 'BROADCAST_EVENT';

const Constants = {
  MODULE_NAME: 'RNBroadcastReceiver' as const,
  DATA_PROP: 'data' as const,
  BROADCAST_EVENT_NAME,
};

export { defaultConfig, Constants };
export type { Event };
