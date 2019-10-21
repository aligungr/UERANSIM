import { create } from 'zustand'

export enum ContentType {
  FLOW_SELECTION = 1,
  FLOW_ACTION = 2,
}

export const [useContentStore] = create(set => ({
  contentType: ContentType.FLOW_SELECTION,
  setContent: (contentType: ContentType) =>
    set(state => ({
      contentType: contentType,

    })),
}))